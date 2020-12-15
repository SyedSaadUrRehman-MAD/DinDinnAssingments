package test.saad.dindinnminiassignment.assingmenttask.ui

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.airbnb.mvrx.*
import com.bumptech.glide.Glide
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_offers.*
import test.saad.dindinnminiassignment.R
import test.saad.dindinnminiassignment.assingmenttask.mvrx.MvRxViewModel
import test.saad.dindinnminiassignment.assingmenttask.datasource.Datasource
import test.saad.dindinnminiassignment.assingmenttask.network.Webservice
import javax.inject.Inject

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

lateinit var layouts: List<String>

data class OffersState(
    val offersAsync: Async<List<String>> = Uninitialized,
    val offers: List<String> = Datasource.offers
) : MvRxState

class OffersViewModel @AssistedInject constructor(
    @Assisted state: OffersState,
    private val webservice: Webservice
) : MvRxViewModel<OffersState>(state) {
    fun getOffers() {
        withState {
            webservice
                .offers()
                .execute {
                    copy(offersAsync = it)
                    copy(offers = Datasource.offers)
                }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: OffersState): OffersViewModel
    }

    companion object : MvRxViewModelFactory<OffersViewModel, OffersState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: OffersState
        ): OffersViewModel? {
            val fragment = (viewModelContext as FragmentViewModelContext).fragment<OffersFragment>()
            return fragment.viewModelFactory.create(state)
        }
    }
}

class OffersFragment : BaseMvRxFragment() {
    @Inject
    lateinit var viewModelFactory: OffersViewModel.Factory
    private lateinit var dots: Array<TextView?>
    private lateinit var mAdapter: ViewsSliderAdapter
    private val viewModel: OffersViewModel by fragmentViewModel()

    /**
     * Override this to handle any state changes from MvRxViewModels created through MvRx Fragment delegates.
     */
    override fun invalidate() = withState(viewModel) { state ->
        init(state.offers)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getOffers()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun init(offers: List<String>) {
        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = offers
        addBottomDots(0)
        mAdapter = ViewsSliderAdapter(requireContext())
        view_pager.setAdapter(mAdapter)
        view_pager.registerOnPageChangeCallback(pageChangeCallback)
    }

    /*
     * Adds bottom dots indicator
     * */
    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls<TextView>(layouts.size)
        layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots.set(i, TextView(requireActivity()))

            dots.get(i)!!.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
            dots.get(i)!!.setText(Html.fromHtml("&#8226;"))
            dots.get(i)!!.setTextSize(resources.getDimension(R.dimen._15ssp))
            dots.get(i)!!.setTextColor(ContextCompat.getColor(requireActivity(), R.color.active))
            dots.get(i)!!.gravity = Gravity.CENTER
            layoutDots.addView(dots.get(i))
        }
        if (dots.size > 0) {
            dots.get(currentPage)!!
                .setTextColor(ContextCompat.getColor(requireActivity(), R.color.active))
            dots.get(currentPage)!!
                .setTextSize(resources.getDimension(R.dimen._25ssp))
        }
        layoutDots.requestLayout()
    }

    private fun getItem(i: Int): Int {
        return view_pager.getCurrentItem() + i
    }


    /*
     * ViewPager page change listener
     */
    var pageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            addBottomDots(position)
        }
    }

    class ViewsSliderAdapter(requireContext: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val context: Context = requireContext
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.offer, parent, false)
            return SliderViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Glide.with(context)
                .load(layouts.get(position))
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into((holder as SliderViewHolder).offer!!)
        }

        override fun getItemViewType(position: Int): Int {
            return 0
        }

        override fun getItemCount(): Int {
            return layouts.size
        }

        inner class SliderViewHolder(view: View?) : RecyclerView.ViewHolder(
            view!!
        ) {
            var offer: ImageView? = view?.findViewById(R.id.ivOffer)
        }
    }
}