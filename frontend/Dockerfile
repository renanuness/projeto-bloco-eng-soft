FROM node:20-alpine AS deps

WORKDIR /app

COPY package.json package-lock.json* ./

RUN npm ci --only=production && \
    cp -R node_modules /prod_node_modules && \
    npm ci

FROM node:20-alpine AS development

WORKDIR /app

RUN apk add --no-cache curl git

COPY --from=deps /app/node_modules ./node_modules
COPY package.json ./

COPY vite.config.js .
COPY index.html .
COPY tailwind.config.js* .
COPY postcss.config.js* .

COPY public ./public
COPY src ./src

EXPOSE 3000

CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]

FROM node:20-alpine AS builder

WORKDIR /app

COPY --from=deps /app/node_modules ./node_modules
COPY . .

ARG VITE_API_URL
ENV VITE_API_URL=${VITE_API_URL:-http://localhost:8080/api}

RUN npm run build


FROM nginx:1.25-alpine AS production

RUN apk add --no-cache curl

RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /app/dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

RUN mkdir -p /var/log/nginx && \
    chown -R nginx:nginx /usr/share/nginx/html /var/log/nginx

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:80/ || exit 1

EXPOSE 80

ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["nginx", "-g", "daemon off;"]