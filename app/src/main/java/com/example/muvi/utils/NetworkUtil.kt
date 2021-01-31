import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object NetworkUtil {
    @RequiresApi(Build.VERSION_CODES.M)
    fun isConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) or
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}
