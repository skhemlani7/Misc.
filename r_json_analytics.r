#included libraries
library(jsonlite)
library(stringr)

# Calculate how many words in a string
nwords <- function(string, pseudo=F){
  ifelse( pseudo,
          pattern <- "\\S+",
          pattern <- "[[:alpha:]]+"
  )
  str_count(string, pattern)
}

#Import json data into a list
json_list = lapply(readLines("review.data"), fromJSON)

#initialize lists to add to data frame and counter
counter <- 0
vector1 <- vector()
vector2 <- vector()
vector3 <- vector()
vector4 <- vector()
vector5 <- vector()

#check for reviews that have >100 words
for(i in 1:10001){
  if(nwords(json_list[[i]][[3]]) > 100){
    counter <- counter + 1
    vector1 = c(vector1, json_list[[i]][[1]])
    vector2 = c(vector2, json_list[[i]][[2]])
    vector3 = c(vector3, json_list[[i]][[3]])
    vector4 = c(vector4, json_list[[i]][[4]])
    vector5 = c(vector5, json_list[[i]][[5]])
  }
}

#create data frame
emp.data <- data.frame(
  reviewerID = vector1,
  asin = vector2,
  text = vector3,
  overall = vector4,
  reviewTime = vector5,
  stringsAsFactors = FALSE
)

#Sort the data frame in descending order and write to file 
write.table(emp.data[order(emp.data$overall, decreasing = TRUE), ], 'result.data')
