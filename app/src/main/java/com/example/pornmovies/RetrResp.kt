package com.example.pornmovies

data class RetrResp(
    var attack_type: String,
    var id: Int,
    var legs: Int,
    var localized_name: String,
    var name: String,
    var primary_attr: String,
    var roles: List<String>
)