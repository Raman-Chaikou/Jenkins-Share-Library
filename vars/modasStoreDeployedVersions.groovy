#!/usr/bin/env groovy

def call(pipelineParams, deploymentStatus) {
    def environment = pipelineParams.ENVIRONMENT
    if (!environment?.trim()) {
        return
    }
    def versionFileName = "/var/jenkins_home/tmp/modas/modas-${environment}.versions";

    storeVersion(versionFileName, 'modas/modas_frontend', pipelineParams.GUI_VERSION, deploymentStatus)
    storeVersion(versionFileName, 'modas/modas_backend_releasing', pipelineParams.RELEASING_VERSION, deploymentStatus)
    storeVersion(versionFileName, 'modas/modas_backend_cataloguing', pipelineParams.CATALOGUING_VERSION, deploymentStatus)
    storeVersion(versionFileName, 'modas/modas_backend_illustrations', pipelineParams.ATTACHMENTS_VERSION, deploymentStatus)
    storeVersion(versionFileName, 'modas/modas_backend_descriptions', pipelineParams.DESCRIPTIONS_VERSION, deploymentStatus)
    storeVersion(versionFileName, 'modas/waf', pipelineParams.WAF_VERSION, deploymentStatus)
    storeVersion(versionFileName, 'modas/lb', pipelineParams.LB_VERSION, deploymentStatus)    
    storeVersion(versionFileName, 'ecat/keycloak', pipelineParams.KEYCLOAK_VERSION, deploymentStatus)

    println "--------------------------\nDeployment info: ${environment}\n${readFile(versionFileName)}\n--------------------------"
}