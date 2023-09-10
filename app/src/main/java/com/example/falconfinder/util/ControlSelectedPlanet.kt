package com.example.falconfinder.util

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ControlSelectedPlanet(initValue:Int = 0,
                            private val maxValue: Int = 4,
                            private val minValue: Int = 0): ReadWriteProperty<Any?, Int> {
    private var count = initValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return count
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if(value in minValue..maxValue){
            count = value
        }
    }

}