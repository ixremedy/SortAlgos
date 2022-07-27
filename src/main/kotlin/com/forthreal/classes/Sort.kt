package com.forthreal.classes

import java.util.*
import java.util.stream.Stream

object Sort {
    fun quickSort(array: LinkedList<Long>, withDebug: Boolean = false) = quickSort(array, 0, array.size - 1, withDebug)

    fun mergeSort(array: LinkedList<Long>, withDebug: Boolean = false) : LinkedList<Long> {
        if(array.size > 1) {
            val iter = array.iterator()
            val leftPart = LinkedList<Long>()
            val rightPart = LinkedList<Long>()
            Stream.generate { iter.next() }.limit((array.size / 2).toLong()).forEach(leftPart::add)
            Stream.generate { iter.next() }.limit(array.size.toLong() - leftPart.size).forEach(rightPart::add)

            if(withDebug) println(">> Left $leftPart\n>> Right $rightPart")

            return merge(mergeSort(leftPart, withDebug), mergeSort(rightPart, withDebug), withDebug)

        }

        return array
    }

    private fun merge(array1: LinkedList<Long>, array2: LinkedList<Long>, withDebug: Boolean = false)
        : LinkedList<Long>
    {
        val arr = LinkedList<Long>()
        var pos1 = 0
        var pos2 = 0

        if(withDebug) println(">> Merge in $array1 $array2")

        while(array1.size > pos1 && array2.size > pos2 ) {
            if(array1[pos1] >= array2[pos2]) {
                arr.add(array2[pos2++])
            } else {
                arr.add(array1[pos1++])
            }
        }

        while(array1.size > pos1) {
            arr.add(array1[pos1++])
        }

        while(array2.size > pos2) {
            arr.add(array2[pos2++])
        }

        if(withDebug) println(">> Merge out $arr")

        return arr
    }

    private fun quickSort(array: LinkedList<Long>, lowerBoundary: Int, upperBoundary: Int, withDebug: Boolean = false) {

        if(lowerBoundary >= upperBoundary) return

        // randomise pivot position selection
        val pivotRand = Random().nextInt( upperBoundary - lowerBoundary ) + lowerBoundary
        swap(array, pivotRand, upperBoundary) // we will always be storing our pivot in the last position

        var lb = lowerBoundary
        var ub = upperBoundary // remember the pivot at the upperBoundary?

        if(withDebug) println(">> pivot ${array[upperBoundary]} lb $lb ub $ub\n>> before: $array")

        while(lb < ub) {
            // keep moving the lower boundary upwards until we find a number greater than pivot
            while(array[lb] <= array[upperBoundary] && lb < ub) lb++
            // keep moving the upper boundary downwards until we find a number lower that the pivot
            while(array[ub] >= array[upperBoundary] && lb < ub) ub--
            swap(array, lb, ub)
        }
        // at this point we should have lB = uB and so we need to return the pivot to this position
        swap(array, lb, upperBoundary)

        if(withDebug) println(">> after: $array lb $lb\n----------------")

        //at this point we have two parts unsorted: the left part (less than the pivot) and
        //the right part (higher than the pivot). Both of them need local sorting
        quickSort(array, lowerBoundary, lb - 1) // let's locally sort the left part to the pivot
        quickSort(array, ub + 1, upperBoundary) // let's locally sort the right part to the pivot
    }

    private fun swap(array: LinkedList<Long>, position1: Int, position2: Int) {
        val tmp = array[position1]
        array[position1] = array[position2]
        array[position2] = tmp
    }
}