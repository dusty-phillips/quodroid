import web
import subprocess
from quodlibet import const

urls = (
        '/(previous|next|play-pause)/', 'QuodControl',
        )

class QuodControl(object):
    def GET(self, control):
        f = file(const.CONTROL, "w")
        f.write(control)
        f.close()
        return(control)

app = web.application(urls, globals())

if __name__ == "__main__": app.run()
