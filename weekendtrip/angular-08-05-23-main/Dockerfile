# Stage 1: Compile and Build angular codebase

# Use official node image as the base image
FROM node:alpine as builder
RUN npm cache clean --force
WORKDIR /ang67
COPY . .
RUN npm i --force
RUN npm run build

# stage 2
FROM nginx:alpine
WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY --from=builder /ang67/dist/week-end-trips .

ENTRYPOINT ["nginx", "-g", "daemon off;"]