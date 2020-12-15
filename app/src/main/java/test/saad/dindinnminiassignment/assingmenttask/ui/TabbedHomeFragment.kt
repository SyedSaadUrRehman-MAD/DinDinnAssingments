package test.saad.dindinnminiassignment.assingmenttask.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.airbnb.mvrx.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_tabbed_home.*
import test.saad.dindinnminiassignment.R
import test.saad.dindinnminiassignment.assingmenttask.mvrx.MvRxViewModel
import test.saad.dindinnminiassignment.assingmenttask.datasource.Datasource
import test.saad.dindinnminiassignment.assingmenttask.network.Webservice
import javax.inject.Inject

data class ProductCategoryState(
    val productCatAsync: Async<List<String>> = Uninitialized,
    val productCategories: List<String> = Datasource.productCategories
) : MvRxState

class TabbedFragmentViewModel @AssistedInject constructor(
    @Assisted state: ProductCategoryState,
    private val webservice: Webservice
) : MvRxViewModel<ProductCategoryState>(state) {
    fun getProductCategories() {
        withState {
            webservice
                .categories()
                .execute {
                    copy(productCatAsync = it)
                    copy(productCategories = Datasource.productCategories)
                }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: ProductCategoryState): TabbedFragmentViewModel
    }

    companion object : MvRxViewModelFactory<TabbedFragmentViewModel, ProductCategoryState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: ProductCategoryState
        ): TabbedFragmentViewModel? {
            val fragment = (viewModelContext as FragmentViewModelContext).fragment<TabbedHomeFragment>()
            return fragment.viewModelFactory.create(state)
        }
    }
}

class TabbedHomeFragment : BaseMvRxFragment() {
    @Inject
    lateinit var viewModelFactory: TabbedFragmentViewModel.Factory
    private val viewModel: TabbedFragmentViewModel by fragmentViewModel()

    /**
     * Override this to handle any state changes from MvRxViewModels created through MvRx Fragment delegates.
     */
    override fun invalidate()  = withState(viewModel) { state ->
        init(state.productCategories)
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabbed_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductCategories()
    }

    private fun init(categories:List<String>) {
        view_pager.setAdapter(ViewPagerFragmentAdapter(requireActivity(),categories))

        // attaching tab mediator
        TabLayoutMediator(
            tab_layout, view_pager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(
                categories.get(
                    position
                )
            )
        }.attach()
    }

    private class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity,val productCategories: List<String>) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            return getFragment(position)
        }

        private fun getFragment(position: Int): Fragment {
            val params = this.productCategories.get(position)
            val frag: BaseProductFragment = BaseProductFragment.newInstance(params, params)
            return frag
        }

        override fun getItemCount(): Int {
            return Datasource.productCategories.size
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TabbedHomeFragment().apply {

            }
    }
}