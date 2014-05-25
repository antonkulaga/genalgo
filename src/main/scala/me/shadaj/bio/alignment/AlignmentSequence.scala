package me.shadaj.bio.alignment

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Builder
import me.shadaj.bio.sequences.BaseLike
import me.shadaj.bio.sequences.Sequence

class AlignmentSequence(innerSequence: IndexedSeq[BaseLike]) extends Sequence[BaseLike, AlignmentSequence] {

  def apply(index: Int) = {
    innerSequence(index)
  }
  def length = innerSequence.length
  
  def seqBuilder: Builder[BaseLike, AlignmentSequence] = AlignmentSequence.newBuilder
}

object AlignmentSequence {
  def fromSeq(seq: IndexedSeq[BaseLike]) = new AlignmentSequence(seq)
  
  def newBuilder: Builder[BaseLike, AlignmentSequence] = new ArrayBuffer mapResult fromSeq
  
  implicit def canBuildFrom = new CanBuildFrom[AlignmentSequence, BaseLike, AlignmentSequence] {
    def apply() = newBuilder
    def apply(from: AlignmentSequence) = newBuilder
  }
}