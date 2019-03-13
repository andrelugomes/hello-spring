package com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin


data class SortedTweet (
    val id : Long,
    val index : String?,
    val text : String?,
    val createdAt : String?
) : Comparable<SortedTweet> {
    
    override fun compareTo(other: SortedTweet) = when {
        this.id > other.id -> 1
        this.id < other.id -> -1
        else -> 0
    }
}

