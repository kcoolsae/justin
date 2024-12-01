#!/bin/bash
# customize-bootstrap.sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Copyright © 2024-2025 Kris Coolsaet (Universiteit Gent)
#
# This software is distributed under the MIT License - see files LICENSE and AUTHORS
# in the top level project directory.
#

#
# Customize bootstrap into justin-bootstrap.min.css
#

if [ scss/justin-bootstrap.scss -nt public/css/justin-bootstrap.min.css ]
then
  # only if source newer than result:
  if ! hash sass 2>/dev/null
  then
      echo >&2 "sass is required"
  elif ! [ -d bootstrap ]
  then
      echo >&2 "bootstrap source code is required"
  else
      sass scss/justin-bootstrap.scss public/css/justin-bootstrap.min.css --style=compressed
      cp bootstrap/node_modules/bootstrap/dist/js/bootstrap.bundle.min.js* public/js
  fi
fi
