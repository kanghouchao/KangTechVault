# syntax=docker/dockerfile:1.10.0
FROM node:23.3-alpine AS build

WORKDIR /workspace

COPY package.json ./

RUN npm install

COPY ./public ./public

COPY ./src ./src

RUN npm run build

FROM nginx:1.26.2-alpine AS package

COPY --from=build /workspace/build /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]