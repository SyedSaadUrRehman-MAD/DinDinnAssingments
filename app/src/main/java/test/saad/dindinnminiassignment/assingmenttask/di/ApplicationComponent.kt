package test.saad.dindinnminiassignment.assingmenttask.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import test.saad.dindinnminiassignment.assingmenttask.mvrx.MvRxApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppAssistedModule::class,
        RetrofitModule::class,
        ActivityFragmentBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MvRxApplication)

    override fun inject(instance: DaggerApplication?)
}
