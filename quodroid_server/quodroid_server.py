import web
from quodlibet import const

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
        f = file(const.CONTROL, "w")
        control = command_map.get(control, control)
        f.write(control)
        f.close()
        return(control)

app = web.application(urls, globals())

if __name__ == "__main__": app.run()
