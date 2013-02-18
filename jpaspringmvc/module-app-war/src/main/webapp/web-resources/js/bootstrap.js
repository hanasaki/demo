/**
 Copyright 2010 Scott Koon

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 **/


var bootstrap = (function() {
    var itemsLoaded = []; //maintain a list of items we have already loaded to prevent duplicates
    var bootstrap = function() {
        var args = arguments;
        if(args.length == 0) return;

        var scriptSrc = "";
        //figure out if we were passed a script URL, a config object, or an array of script URLs
        var src = args[0];

        if(typeof src === "string") {
            scriptSrc =src;
        } else if(typeof src == "object" || typeof src == "array") {
            processConfigObject(src);
        }

        var head = document.head || document.getElementsByTagName("head")[0];
        var scriptId = args[1] || "script" + Math.floor(Math.random() * 1234);
        var loaded = false;
        var scriptTag = null;
        var callback = callback = args[2] || null;

        function notLoaded(src, type) {
            var nodeList;
            var propToCheck;
            var i = 0, l = itemsLoaded.length;
            while (i < l) {
                if (itemsLoaded[i] == scriptSrc) return false;
                i++
            }

            //last ditch effort to see if the script we are trying to load
            // was already loaded using a static script tag.
            if (type === "js") {
                var nodeList = document.getElementsByTagName("script");
                propToCheck = "src";
            } else if (type === "css") {
                var nodeList = document.getElementsByTagName("link");
                propToCheck = "href";
            }

            var nodeArray = (function() {
                    var a = [];
                    for(var c = nodeList.length;c-- > 0;) {
                        if(c in nodeList) {
                            a.push(nodeList.c)
                        }
                    }
                    return a
                    }); 
            for (var c = 0, len = nodeArray.length; c < len; c++) {
                if ((propToCheck in nodeArray[c]) && nodeArray[c][propToCheck] == src)
                    return false;
            }
            return true;

        }

        if (scriptSrc.indexOf(".js") > -1 && notLoaded(scriptSrc, "js")) {
            itemsLoaded.push(scriptSrc);
            scriptTag = createScriptElement(scriptSrc, scriptId);
        }


        if (scriptSrc.indexOf(".css") > -1 && notLoaded(scriptSrc, "css")) {
            itemsLoaded.push(scriptSrc);
            scriptTag = createCssElement(scriptSrc, scriptId);
        }

        if (scriptTag) {
            scriptTag.onload = function() {
                loaded = true;
            }

            scriptTag.onreadystatechange = function() {
                if (!loaded) {
                    if (this.readyState == "loaded" || this.readyState == "complete") {
                        loaded = true;
                    }
                }
            }
            head.appendChild(scriptTag);
        }

        function makeCallback() {
            window.clearInterval(id);
            if (callback != null) {
                callback();
            }
        }


        var id = window.setInterval(function() {
            if (loaded) {
                makeCallback();
            }
        }, 0)
    }

    function createScriptElement(scriptSrc, scriptId) {
        var scriptTag = document.createElement("script");
        scriptTag.setAttribute("type", "text/javascript");
        scriptTag.src = scriptSrc;
        scriptTag.id = scriptId;
        return scriptTag;
    }

    function createCssElement(scriptSrc, scriptId) {
        var link = document.createElement("link");
        link.rel = "stylesheet";
        link.type = "text/css";
        link.href = scriptSrc;
        link.id = scriptId;
        return link;
    }

    function processConfigObject(config) {
        for(var script in config) {
            bootstrap(config[script], script);
        };
    }
    return (window.bootstrap = window.$b = bootstrap);
}())
