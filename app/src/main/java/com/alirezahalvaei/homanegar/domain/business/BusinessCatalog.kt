package com.alirezahalvaei.homanegar.domain.business

data class BusinessPalette(val primary:Long,val secondary: Long, val tertiary: Long)

object BusinessCatalog {
    val defaultPalette= BusinessPalette(0xFF4F46E5, 0xFF7C3AED, 0xFF06B6D4)
}

