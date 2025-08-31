package enums

import com.foodics.domain.R

enum class BottomNavItem(val title: String, val icon: Int) {
    Tables("Tables", R.drawable.ic_restaurant),
    Orders("Orders", R.drawable.ic_order),
    Settings("Settings", R.drawable.ic_seetings),
    Menu("Menu", R.drawable.ic_side_menu),
}