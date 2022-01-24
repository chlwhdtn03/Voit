package com.chlwhdtn.voit

data class Debate(
    val id: String,
    val title: String,
    val message: HashMap<String, String>,

    val ticket_agree: Int,
    val ticket_disagree: Int
)
