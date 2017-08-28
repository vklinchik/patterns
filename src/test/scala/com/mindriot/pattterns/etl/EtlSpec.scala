package com.mindriot.pattterns.etl

import java.io.File

import com.mindriot.patterns.etl.Extractor
import org.scalatest.AsyncWordSpec
import com.mindriot.pattterns.etl._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class EtlTest  extends AsyncWordSpec {

  /*
  case class TestRecord(id: Int, value: Int)

  val testData =
    Seq(
      TestRecord(1, 10),
      TestRecord(2, 20),
      TestRecord(3, 30)
    )

  trait TestReport extends Extractor {
    type ExtractorInput = (Campaign, DateTime)
    type ExtractorOutput = Seq[TestRecord]

    def extract(input: ExtractorInput)(implicit ec: ExecutionContext): Future[ExtractorOutput] = {
      Future.successful(testData)
    }
  }

  trait TestTransformer extends Transformer {
    type TransformerInput = Seq[TestRecord]
    type TransformerOutput = File

    def transform(data: TransformerInput)(implicit ec: ExecutionContext): Future[TransformerOutput] = Future {
      val file = createTempFile("report", ".csv")
      printToFile(file) { printer =>
        data.foreach(r => printer.println(r.productIterator.mkString(",")))
      }
      file
    }

    def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
      val p = new java.io.PrintWriter(f)
      try { op(p) } finally { p.close() }
    }

    def createTempFile(prefix: String, suffix: String): File = {
      val file = File.createTempFile("report", ".txt")
      file.deleteOnExit
      file
    }

  }

  trait TestUploader extends Loader {
    type LoaderOutput = Seq[TestRecord]
    type LoaderInput = File

    def load(data: LoaderInput)(implicit ec: ExecutionContext): Future[LoaderOutput] = Future {
      import scala.io.Source

      Source
        .fromFile(data)
        .getLines
        .map(_.split(","))
        .map(array => (TestRecord.apply _).tupled((array(0).toInt, array(1).toInt)))
        .toSeq
    }
  }

  class TestRunner extends Runner
    with TestReport
    with TestTransformer
    with TestUploader {

    override type TransformerInput = ExtractorOutput
    override type TransformerOutput = File
    override type LoaderInput = TransformerOutput

    def run(input: ExtractorInput)(implicit ec: ExecutionContext): Future[LoaderOutput] = {
      for {
        data <- extract(input)
        transformed <- transform(data)
        status <- load(transformed)
      } yield status
    }
  }

  "Automated Report Runner" should {
    "Generate full report" in {
      val runner = new TestRunner()

      val response = runner.run((campaign, DateTime.now))
      response.map(result => assert(testData == result))

    }
  }
  */
}