#!/usr/bin/env groovy

def String readVersion(String fileName, String imageName) {
    if (!imageName?.trim()) {
        println 'Specify image name'
        return
    }

    if (fileExists(fileName)) {
        def prefix = "${imageName}"
        def pattern = /${prefix}:([\w\d._-]*)/
        def content = readFile fileName
        
        def image = (content =~ /$pattern/)[0][1]
        return image

    } else {
        println "${fileName} does not exist"
        return
    }
}