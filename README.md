# Sentence Processor

> Parses file and identifies sentences boundaries. Outputs in XML.


## Getting Started

### Dependencies

1. Install [Java]
1. Install [Gradle]

### Configuration

## Tasks

To run program locally:

    $ gradle distZip
    $ cd build/distributions
    $ unzip sentence_processor.zip
    $ cd sentence_processor/bin
    $ ./SentenceProcessor

### Limitations

1. Application will only detect sentences if they start with an uppercase letter and end with a period, question mark, 
 exclamation mark or a period followed by a single or double quotation. 
1. The sentence processor will not be able to detect a sentence within quotations, they are considered part of the 
current sentence structure. Inner quotations are also detected. 
1. Sentences separated by a semicolon will not be detected. 
1. Possession will also be detected, but the application will not distinguish between plural possession or the end of 
a quote using a single apostrophe.

[Gradle]: http://gradle.org/downloads
[Java]: http://www.oracle.com/technetwork/java/javase/downloads
