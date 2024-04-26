package app.gratum.kawaishiryuappnew.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import app.gratum.kawaishiryuappnew.R
import app.gratum.kawaishiryuappnew.databinding.ActivityMainMenuHostBinding
import app.gratum.kawaishiryuappnew.ui.fragment.home.HomeFragment
import app.gratum.kawaishiryuappnew.ui.fragment.location.LocationFragment
import app.gratum.kawaishiryuappnew.ui.fragment.profile.ProfileUserFragment
import app.gratum.kawaishiryuappnew.ui.fragment.tec_menu.MenuJiuJitsuFragment
import app.gratum.kawaishiryuappnew.ui.fragment.tec_menu.MenuJudoFragment
import com.google.android.material.navigation.NavigationView

class MainMenuHostActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainMenuHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) //Toolbar Personalizada
        binding.navView.setNavigationItemSelectedListener(this) //NavView(Menu de item)

        //Permite al usuario cambiar entre 2 estados activado o desactivado
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            binding.toolbar,
            R.string.open_nav,
            R.string.close_nav
        )

        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.navView.setCheckedItem(R.id.homeFragment)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> replaceFragment(HomeFragment())
            R.id.menuJudoFragment -> replaceFragment(MenuJudoFragment())
            R.id.menuJiuJitsuFragment -> replaceFragment(MenuJiuJitsuFragment())
            R.id.locationFragment -> replaceFragment(LocationFragment())
            R.id.profileUserFragment -> replaceFragment(ProfileUserFragment())
            R.id.dialogSignOutUser -> replaceFragment(DialogFragment())

            else -> Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
        }
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}