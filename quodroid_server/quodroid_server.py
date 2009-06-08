import web
import subprocess
from quodlibet import const

urls = (
        '/(previous|next|play-pause|volume-up|volume-down)/', 'QuodControl',
        )

command_map = {
        'volume-up': 'volume +',
        'volume-down': 'volume -'
        }

class QuodControl(object):
    def GET(self, control):
        f = file(const.CONTROL, "w")
        control = command_map.get(control, control)
        f.write(control)
        f.close()
        return(control)

app = web.application(urls, globals())

if __name__ == "__main__": app.run()
