# Subhojit Profile

Dynamic Spring Boot + Thymeleaf profile app with H2-backed admin editing.

## Deploy Free On Render

1. Open this one-click deploy link while signed in to Render:
   https://render.com/deploy?repo=https://github.com/subhojits/subhojit-profile
2. Keep plan as `Free` and create the service.
3. Wait for build to finish and open the public URL provided by Render.

## Routes

- `/` Public profile
- `/admin` Admin editor
- `/h2-console` H2 console
- `/Profile.pdf` Resume download

## Note

H2 is in-memory, so data resets when the server restarts.
