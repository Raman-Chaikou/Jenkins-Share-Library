#!/usr/bin/env groovy

def storeVersion(String versionFileName, String imageName, String imageVersion, String deploymentStatus) {
    if (!imageVersion?.trim()) {
        return
    }
    def versionInfo = "${imageName}:${imageVersion} - ${deploymentStatus}"
    if (fileExists(versionFileName)) {
        def prefix = "${imageName}"
        def pattern = /${prefix}:.+/
        def content = readFile versionFileName
        if (content =~ pattern) {
            writeFile file: versionFileName, text: content.replaceAll(pattern, versionInfo)
        } else {
            writeFile file: versionFileName, text: "${content}\n${versionInfo}"
        }
    } else {
        sh 'pwd'
        writeFile file: versionFileName, text: versionInfo
    }
}