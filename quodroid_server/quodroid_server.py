#! /usr/bin/env python
# Copyright (c) 2009 Dusty Phillips <dusty@linux.ca>

# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:

# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.

# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

import web
from quodlibet import const

STATUS_FORMAT = ('%(title)s - %(artist)s')

urls = (
        '/(previous|next|play-pause|volume-up|volume-down)/', 'QuodControl',
        )

command_map = {
        'volume-up': 'volume +',
        'volume-down': 'volume -'
        }

class QuodControl(object):
    '''When retrieved with a GET request, take the control parameter and pass
    it on to quodlibet's control pipe, possibly filtering it through command
    map to ensure the correct string is passed. Returns a status string naming
    the current song formatted according to the STATUS_FORMAT.'''
    def GET(self, control):
        with file(const.CONTROL, "w") as f:
            control = command_map.get(control, control)
            f.write(control)

        with file(const.CURRENT) as f:
            info = dict([line.strip().split('=') for line in f])

        return(STATUS_FORMAT % info)

app = web.application(urls, globals())

if __name__ == "__main__": app.run()
