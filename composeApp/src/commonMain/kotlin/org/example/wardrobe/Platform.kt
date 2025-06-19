package org.example.wardrobe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform