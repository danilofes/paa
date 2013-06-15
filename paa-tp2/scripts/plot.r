
# Dimension
t <- read.table("dimension.csv", header=FALSE, sep=';')
pdf(file="dimension.pdf", height=5, width=5)
plot(t[,1], t[,2],  type="o", pch=21, lty=1, xlab="Tamanho da imagem", ylab="Tempo de execução (ms)")
lines(t[,1], t[,3], type="o", pch=22, lty=2)
legend("topleft", c("Dinâmico", "Grafo"), cex=0.8, lty=1:2, pch=21:22, lwd=1, bty="n")
dev.off()

# Convolution
t <- read.table("convolution.csv", header=FALSE, sep=';')
pdf(file="convolution.pdf", height=5, width=5)
plot(t[,1], t[,2],  type="o", pch=22, lty=1, xlab="Tamanho da matriz", ylab="Tempo de execução (ms)")
dev.off()
