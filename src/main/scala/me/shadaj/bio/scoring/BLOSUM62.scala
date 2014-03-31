package me.shadaj.bio.scoring

import scala.io.Source
import me.shadaj.bio.sequences.BaseLike
import me.shadaj.bio.sequences.BaseLike
import me.shadaj.bio.sequences.AminoAcid

class BLOSUM62(val indelPenalty: Int) extends ScoringMatrix[AminoAcid] {
  private val source = Source.fromInputStream(getClass().getResourceAsStream("/blosum62.txt")).getLines
  private val xAxis = source.next.trim.split(' ').filter(_ != "").map(_.head)
  private val scorer = source.flatMap { s =>
    val y = AminoAcid.fromChar(s.head)
    s.tail.trim.split(' ').filter(s => s != " " && s != "").map(_.toInt).zipWithIndex.map { case (score, index) =>
      (AminoAcid.fromChar(xAxis(index)), y) -> score
    }
  }.toMap
  
  def score(b1: AminoAcid, b2: AminoAcid) = {
    scorer((b1, b2))
  }
}