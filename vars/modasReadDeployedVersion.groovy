#!/usr/bin/env groovy

def String call(imageName, environment) {
    if (!imageName?.trim()) {
        println 'Specify image name'
        return
    }

    def fileName = getFileNameWithDeployedVersions(environment)

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