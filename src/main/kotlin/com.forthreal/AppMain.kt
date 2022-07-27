package com.forthreal

import com.forthreal.classes.Sort
import java.util.*
import java.util.stream.Stream

class AppMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val rand = Random()
            val arr = LinkedList<Long>()
            //Stream.generate{ rand.nextLong() }.filter { it > 0 }.limit(10).forEach(arr::add)
            Stream.generate{ rand.nextInt(100) }.filter { it > 0 }.limit(10).map { it.toLong() }.forEach(arr::add)

            println("Before: $arr")

            Sort.quickSort(arr)

            println("After: $arr")
        }
    }
}