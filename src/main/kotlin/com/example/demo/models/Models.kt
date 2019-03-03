package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonCreator
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SomeModel(val name: String, val id: Int)

data class InputModel constructor(val id: String = "", @field:NotNull @field:NotBlank val name: String?) {
}