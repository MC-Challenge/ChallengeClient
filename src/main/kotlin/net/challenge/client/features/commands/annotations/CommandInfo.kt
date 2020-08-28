package net.norisk.core.features.command.annotations

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CommandInfo(val name: String, val description: String = "", val syntax: String = "", val aliases: String = "")
