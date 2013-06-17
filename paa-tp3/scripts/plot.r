
# Dimension
t <- read.table("timeByN.csv", header=FALSE, sep=';')
pdf(file="timeByN.pdf", height=5, width=5)
plot(t[,1], t[,2],  type="o", pch=22, lty=1, xlab="Tamanho da entrada (n)", ylab="Tempo de execução (ms)")
dev.off()
