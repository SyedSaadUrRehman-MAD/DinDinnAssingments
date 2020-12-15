package test.saad.dindinnminiassignment.assingmenttask.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import test.saad.dindinnminiassignment.assingmenttask.ui.*

@Module
abstract class ActivityFragmentBuilder {
    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun bindOffersFragment(): OffersFragment

    @ContributesAndroidInjector
    abstract fun bindTabbedHomeFragment(): TabbedHomeFragment

    @ContributesAndroidInjector
    abstract fun bindBaseProductFragment(): BaseProductFragment
}
