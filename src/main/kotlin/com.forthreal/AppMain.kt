package com.forthreal

import com.forthreal.classes.Sort
import java.util.*
import java.util.stream.Stream

class AppMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val rand = Random()
            val size = 100L
            val arr = LinkedList<Long>()
            val arr1 = LinkedList<Long>()
            Stream.generate{ rand.nextInt(10000).toLong() }.filter { it > 0 }.limit(size).forEach(arr::add)
            Stream.generate{ rand.nextInt(10000).toLong() }.filter { it > 0 }.limit(size).forEach(arr1::add)

            println("${size} records\n----------------\nQuick sort:\nBefore: $arr")
            val start1 = System.currentTimeMillis()
            Sort.quickSort(arr)
            val end1 = System.currentTimeMillis()
            println("After: $arr\n time: ${end1 - start1}ms")

            println("----------------\nMerge sort:\nBefore: $arr1")
            val start2 = System.currentTimeMillis()
            val newArr2 = Sort.mergeSort(arr1)
            val end2 = System.currentTimeMillis()
            println("After: $newArr2\n time: ${end2 - end1}ms")
        }
    }
}