/** @type {import('next').NextConfig} */
const nextConfig = {
    env: {
        PRODUCT_API_URL: process.env.PRODUCT_API_URL,
    },
    transpilePackages: ['@mui/x-charts']
}

module.exports = nextConfig
