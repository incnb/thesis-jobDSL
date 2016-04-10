job('banch_develop_test/ListJSON_trigger_scm') {

    label('git')

    logRotator {
      numToKeep(30)
      artifactNumToKeep(10)
    }

    scm {
         github('blobbsen/thesis-ListJSON.git','*/develop')
    }

    steps {

        shell('''
              tar -zcvf ListJSON.tgz ListJSON
              tar -zcvf ListJSONtest.tgz ListJSONtest

              rm -rf .git/
              rm -rf ListJSON/
              rm -rf ListJSONtest/
        '''.stripIndent())
    }

    publishers {
      archiveArtifacts('*.tgz')
      downstream('branch_develop_test/ListJSON_build_develop', 'SUCCESS')
    }
}
