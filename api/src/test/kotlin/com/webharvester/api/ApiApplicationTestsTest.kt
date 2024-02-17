@Test
fun testCreateProduct() {
    // Preparar dados de entrada
    val product = Product(name = "Product Name", price = 9.99)

    // Chamar o endpoint
    val response = restTemplate.postForEntity("/api/product", product, Product::class.java)

    // Verificar o código de status da resposta
    assertEquals(HttpStatus.CREATED, response.statusCode)

    // Verificar se o produto foi criado corretamente
    val createdProduct = response.body
    assertNotNull(createdProduct)
    assertEquals("Product Name", createdProduct.name)
    assertEquals(9.99, createdProduct.price, 0.01)
}