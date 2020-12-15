package test.saad.dindinnminiassignment.assingmenttask.mvrx

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import test.saad.dindinnminiassignment.assingmenttask.di.DaggerApplicationComponent

class MvRxApplication : DaggerApplication() {

    /* Implementations should return an [AndroidInjector] for the concrete [ ]. Typically, that injector is a [dagger.Component].
    */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .applicationContext(this)
            .build()
            .also {
                it.inject(this)
            }
}
