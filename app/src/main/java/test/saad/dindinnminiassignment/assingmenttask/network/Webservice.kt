package test.saad.dindinnminiassignment.assingmenttask.network
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import test.saad.dindinnminiassignment.assingmenttask.model.Product

interface Webservice {

    @GET("offers")
    fun offers(): Single<List<String>>

    @GET("products")
    fun categories(): Single<List<String>>

    @GET("products/{name}")
    fun productsList(@Path("name") name: String): Single<List<Product>>
}
