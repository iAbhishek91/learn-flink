# Difference b/w batch and stream processing

## Batch

- Set of data is collected over a period of time and then processed at a single shot.
- job are run at regular interval or on demand with a **bounded** dataset.
- in batch processing we are more concerned with how much data is processed, then the time taken

DATA-1 >
        >
DATA-2   >>>> Batch file >> PROCESSING ENGINE >> Output
        >
DATA-3 >

**Use case**:

where access to all the data is required, mainly for getting insight/analyse old data. For example "difference in sales after discount", "find loyal customer of a bank".

## Stream(real-time processing)

- Data is fed to processing engine as soon as it is generated.
- job runs continuously whenever data is available with **unbounded** data.
- in stream processing we are more concerned with how quickly data is processed, then how much data is processed.

REAL TIME data >>>>>>>> PROCESSING ENGINE >> Output

**Use case**:

Used where we need to analytics in realtime. For example " fraud detection", "social media sentiment analysis".
