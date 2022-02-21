# Characteristic of dataset or datastream

- Dataset are **immutable**. We can change the element within it.
- Every operation on a dataset will **result in a new dataset**. for example: readtextfile() function created a dataset which is then passed on to function fileter1() and dataset-2 is created.
- We can perform **only ONE operation on a dataset**. Multiple operation is not allowed.
- Also the operation is **applied on WHOLE of the dataset**. *partial dataset is never updated/modified by a operation*.
- Dataset **stores list of its dependencies**. That means each dataset know on which dataset it depends.
- We need to mention the type of data while storing the dataset. For example String.
