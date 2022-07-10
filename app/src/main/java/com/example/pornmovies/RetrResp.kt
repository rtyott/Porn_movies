package com.example.pornmovies

data class RetrResp(
    var attack_type: String,
    var legs: Int,
    var localized_name: String,
    var name: String,
    var primary_attr: String,
    var roles: List<String>,
    var img: String,
    var icon: String
)