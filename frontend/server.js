const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

app.use('/api', createProxyMiddleware({ target: 'http://localhost:9192', changeOrigin: true }));
app.use(express.static(path.join(__dirname, 'public')));

app.listen(PORT, () => console.log(`Frontend running on http://localhost:${PORT}`));
