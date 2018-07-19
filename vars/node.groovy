/*
 * Copyright (C) 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
def call(String label, Closure body) {
  return steps.node(label) {
    def oc_home = tool 'oc-v3.9.0-191fece'

    sh "${oc_home}/oc cluster up"
    try {
      body()
    } finally {
      sh "${oc_home}/oc cluster down"
    }
  }
}

