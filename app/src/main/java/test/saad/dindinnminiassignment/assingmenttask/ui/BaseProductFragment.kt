package test.saad.dindinnminiassignment.assingmenttask.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_base_product.*
import test.saad.dindinnminiassignment.R
import test.saad.dindinnminiassignment.assingmenttask.mvrx.MvRxViewModel
import test.saad.dindinnminiassignment.assingmenttask.datasource.Datasource
import test.saad.dindinnminiassignment.assingmenttask.model.Product
import test.saad.dindinnminiassignment.assingmenttask.network.Webservice
import javax.inject.Inject

private const val ARG_PRODUCT_TYPE = "productType"
private const val ARG_PRODUCT_NAME = "productName"


data class ProductsState(
    val productsAsync: Async<List<Product>> = Uninitialized,
    val productsList: List<Product> = Datasource.pizzaProducts
) : MvRxState

class ProductListViewModel @AssistedInject constructor(
    @Assisted state: ProductsState,
    private val webservice: Webservice
) : MvRxViewModel<ProductsState>(state) {
    fun getProductList(type: String) {
        withState {
            webservice
                .productsList(type)
                .execute {
                    copy(productsAsync = it)
                    when (type) {
                        "Pizza" -> {
                            copy(productsList = Datasource.pizzaProducts)
                        }
                        "Sushi" -> {
                            copy(productsList = Datasource.shushiProducts)
                        }
                        "Drinks" -> {
                            copy(productsList = Datasource.drinkProducts)
                        }
                        else ->
                            copy(productsList = Datasource.pizzaProducts)
                    }
                }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: ProductsState): ProductListViewModel
    }

    companion object : MvRxViewModelFactory<ProductListViewModel, ProductsState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: ProductsState
        ): ProductListViewModel? {
            val fragment =
                (viewModelContext as FragmentViewModelContext).fragment<BaseProductFragment>()
            return fragment.viewModelFactory.create(state)
        }
    }
}


class BaseProductFragment : BaseMvRxFragment() {
    @Inject
    lateinit var viewModelFactory: ProductListViewModel.Factory
    private val viewModel: ProductListViewModel by fragmentViewModel()

    /**
     * Override this to handle any state changes from MvRxViewModels created through MvRx Fragment delegates.
     */
    override fun invalidate() = withState(viewModel) { state ->
        init(state.productsList)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private lateinit var mProductAdapter: ProductsRecyclerAdapter
    private var productType: String? = null
    private var typeName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productType = it.getString(ARG_PRODUCT_TYPE)
            typeName = it.getString(ARG_PRODUCT_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductList(productType!!)
    }

    fun init(productList: List<Product>) {
        mProductAdapter =
            ProductsRecyclerAdapter(requireActivity(), productList)

        mProductAdapter.SetOnItemClickListener(object :
            ProductsRecyclerAdapter.OnItemClickListener {
            override fun onAddToCart(view: View?, position: Int) {
                if(requireActivity() is HomeActivity)
                {
                    (requireActivity() as HomeActivity).getAddtoCartCallback().addTocart(productList.get(position))
                }
            }
        })
        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = mProductAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(type: String, name: String) =
            BaseProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PRODUCT_TYPE, type)
                    putString(ARG_PRODUCT_NAME, name)
                }
            }
    }

    private class ProductsRecyclerAdapter(
        private val context: FragmentActivity,
        private var productList: List<Product>
    ) : RecyclerView.Adapter<ProductsRecyclerAdapter.ViewHolder>() {

        private lateinit var mItemClickListener: OnItemClickListener

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = productList[position]
            holder.btnAddCart.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    mItemClickListener?.onAddToCart(v, position)
                    holder.btnAddCart.visibility = INVISIBLE
                    holder.btnAdded1.visibility = VISIBLE
                    Handler().postDelayed(Runnable {
                        holder.btnAdded1.visibility = INVISIBLE
                        holder.btnAddCart.visibility = VISIBLE
                    }, 2000)
                }
            })

            holder.btnAddCart.setText("${item.price} ${item.currency}")
            holder.tvTitle.setText(item.title)
            holder.tvDesc.setText(item.desc)
            holder.tvCartDisplay.setText(item.perOrderCharge)
            holder.tvIshot.visibility = if (item.isHot) VISIBLE else INVISIBLE

            if (item.image != null) {
                Glide.with(context).load(item.image)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .fitCenter()
                    .into(holder.ivProductImage)
                holder.ivProductImage.setPadding(0, 0, 0, 0)
            }
        }

        override fun getItemCount(): Int = productList.size

        class ViewHolder(
            itemView: View
        ) :
            RecyclerView.ViewHolder(itemView) {
            var tvTitle: TextView
            var tvDesc: TextView
            var tvCartDisplay: TextView
            var tvIshot: TextView
            var btnAddCart: Button
            var btnAdded1: Button
            var ivProductImage: ImageView
            var container: ViewGroup

            init {
                tvTitle = itemView.findViewById<View>(R.id.tvTitle) as TextView
                ivProductImage = itemView.findViewById<View>(R.id.ivProductImage) as ImageView
                tvDesc = itemView.findViewById(R.id.tvDesc)
                tvCartDisplay = itemView.findViewById(R.id.tvOrderChars)
                container = itemView.findViewById(R.id.llAddCartContainer)
                btnAddCart = itemView.findViewById(R.id.btnAddtoCart)
                btnAdded1 = itemView.findViewById(R.id.btnAdded1)
                tvIshot = itemView.findViewById(R.id.isHot)
            }
        }

        interface OnItemClickListener {
            fun onAddToCart(view: View?, position: Int)
        }

        //to be added if more suitable
        fun SetOnItemClickListener(itemClickListener: OnItemClickListener) {
            mItemClickListener = itemClickListener
        }

        fun setList(list: List<Product>) {
            this.productList = list
            notifyDataSetChanged()
        }
    }
}