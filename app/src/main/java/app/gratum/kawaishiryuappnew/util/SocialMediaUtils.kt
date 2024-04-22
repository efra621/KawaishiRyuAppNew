package app.gratum.kawaishiryuappnew.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

object SocialMediaUtils {

    fun openWhatsApp(context: Context, phoneNumber: String) {
        val url = "https://api.whatsapp.com/send?phone=+$phoneNumber"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+$phoneNumber")
                )
            )
        }
    }

    fun openGoogleMaps(context: Context, latitude: Double, longitude: Double) {
        val label = "Dojo"
        val uri = "geo:$latitude,$longitude?q=$latitude,$longitude($label)"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.d("???", "SocialMediaUtils")
        }
    }

    fun openInstagram(context: Context, username: String) {
        val uri = Uri.parse("http://instagram.com/_u/$username")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        intent.setPackage("com.instagram.android")

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/$username")
                )
            )
        }
    }

    fun openGoogleDocs(context: Context) {
        val uri =
            Uri.parse("https://docs.google.com/document/d/16YJoxPJ9AqwH1-bdiESlotcfMHELYGeCwew2YzILRyo/edit?usp=sharing")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Manejar la excepción si no hay una aplicación compatible para abrir el enlace
            Log.e("SocialMediaUtils", "No se pudo abrir Google Docs: ${e.message}")
        }
    }

}
