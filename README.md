# Sentence Processor

> Parses files and identifies sentences boundaries. 
> Also, it parses a file for named entities and finds matches in identified sentences. Outputs in XML.


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
1. Named entities processing is very simple. They are only detected if separated by a new line.

[Gradle]: http://gradle.org/downloads
[Java]: http://www.oracle.com/technetwork/java/javase/downloads
