package com.mindriot.patterns


/**
  * Purpose of scala type system:
  *   http://www.artima.com/scalazine/articles/scalas_type_system.html
  * Abstract Type Members versus Generic Type Parameters in Scala:
  *   http://www.artima.com/weblogs/viewpost.jsp?thread=270195
  */
package object etl {

  // Generic interface
  trait Extractor[Input, Out] {
    def extract(input: Input): Out
  }

  trait Transformer[Input, Out] {
    def transform(input: Input): Out
  }

  trait Loader[Input, Out] {
    def load(input: Input): Out
  }

  trait Runner[Input, TransformerInput, LoadInput, Output] {

    def extractor: Extractor[Input, TransformerInput]
    def transformer: Transformer[TransformerInput, LoadInput]
    def loader: Loader[LoadInput, Output]

    def run(input: Input): Output = {
      val data = extractor.extract(input)
      val transformed = transformer.transform(data)
      val result = loader.load(transformed)
      result
    }
  }

  // Abstract types
  trait Extractor2 {
    type ExtractInput
    type ExtractOut
    def extract(input: ExtractInput): ExtractOut
  }

  trait Transformer2 {
    type TransformInput
    type TransformOut
    def transform(input: TransformInput): TransformOut
  }

  trait Loader2 {
    type LoadInput
    type LoadOut
    def load(input: LoadInput): LoadOut
  }

  trait Runner2 {

    val extractor: Extractor2
    val transformer: Transformer2 { type TransformInput = extractor.ExtractOut }
    val loader: Loader2 { type LoadInput = transformer.TransformOut }

    import extractor._
    import loader._

    def run(input: ExtractInput): LoadOut = {
      val data = extractor.extract(input)
      val transformed = transformer.transform(data)
      val result = loader.load(transformed)
      result
    }
  }

}

