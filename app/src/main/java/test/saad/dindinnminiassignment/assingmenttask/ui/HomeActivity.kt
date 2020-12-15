package test.saad.dindinnminiassignment.assingmenttask.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.mvrx.*
import com.google.android.material.snackbar.Snackbar
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import test.saad.dindinnminiassignment.R
import test.saad.dindinnminiassignment.assingmenttask.datasource.Datasource
import test.saad.dindinnminiassignment.assingmenttask.model.Product
import test.saad.dindinnminiassignment.assingmenttask.mvrx.MvRxViewModel
import test.saad.dindinnminiassignment.assingmenttask.network.Webservice
import javax.inject.Inject

class HomeActivity : BaseMvRxActivity(), SlidingUpPanelLayout.PanelSlideListener,
    HasSupportFragmentInjector {
    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
    val productsInCart: MutableList<Product> = mutableListOf<Product>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsInCart.clear()
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_home)
        counter_fab.setOnClickListener { view ->
            if (productsInCart.size > 0) {
                startActivity(Intent(HomeActivity@ this, CartActivity::class.java))
            }
        }
        sliding_layout.addPanelSlideListener(this)
        updateBadge(0)
    }

    private fun updateBadge(count: Int) {
        counter_fab.count = count
    }

    /**
     * Called when a sliding pane's position changes.
     *
     * @param panel       The child view that was moved
     * @param slideOffset The new offset of this sliding pane within its range, from 0-1
     */
    override fun onPanelSlide(panel: View?, slideOffset: Float) {
        offersFragmentContainer.animate()?.y(-slideOffset * 800)?.setDuration(0)?.start()
    }

    /**
     * Called when a sliding panel state changes
     *
     * @param panel The child view that was slid to an collapsed position
     */
    override fun onPanelStateChanged(
        panel: View?,
        previousState: SlidingUpPanelLayout.PanelState?,
        newState: SlidingUpPanelLayout.PanelState?
    ) {
        if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            counter_fab.visibility = VISIBLE
            cardViewPanel.radius = 0f
        } else {
            counter_fab.visibility = GONE
            cardViewPanel.radius = resources.getDimension(R.dimen._20sdp)
        }
    }

    fun getAddtoCartCallback(): AddtoCartCallback {
        return object : AddtoCartCallback {
            override fun addTocart(product: Product) {
                productsInCart.add(product)
                updateBadge(productsInCart.size)
            }
        }
    }

    interface AddtoCartCallback {
        fun addTocart(product: Product)
    }
}