import web
import subprocess

urls = (
        '/prev/', 'Previous',
        '/next/', 'Next',
        '/pause/', 'Pause',
        )

class Previous(object):
    def GET(self):
        # quod is python, should put this whole shebang in a plugin
        # for now, subprocess it
        command = "/usr/bin/quodlibet --previous"
        retcode = subprocess.call(command, shell=True)
        return('previous')

class Next(object):
    def GET(self):
        # quod is python, should put this whole shebang in a plugin
        # for now, subprocess it
        command = "/usr/bin/quodlibet --next"
        retcode = subprocess.call(command, shell=True)
        return('next')

class Pause(object):
    def GET(self):
        # quod is python, should put this whole shebang in a plugin
        # for now, subprocess it
        command = "/usr/bin/quodlibet --play-pause"
        retcode = subprocess.call(command, shell=True)
        return('pause')


app = web.application(urls, globals())

if __name__ == "__main__": app.run()
