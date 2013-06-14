
# Backtracking
t <- read.table("back.csv", header=FALSE, sep='\t')
pdf(file="back.pdf", height=5, width=5)
plot(t[,1], t[,2],  type="l", pch=23, lty=3, xlab="n", ylab="Comparações")
lines(t[,1], t[,3], type="o")
lines(t[,1], t[,4], type="l", pch=22, lty=2)
legend("topleft", c("Normal", "Sem casamentos", "Idêntica"), cex=0.8, lty=1:3, pch=21:23, lwd=1, bty="n")
dev.off()

# Dynamic
t <- read.table("dyna.csv", header=FALSE, sep='\t')
pdf(file="dyna.pdf", height=5, width=5)
plot(t[,1], t[,4],  type="l", pch=22, lty=2, xlab="n", ylab="Comparações")
lines(t[,1], t[,3], type="o")
lines(t[,1], t[,2], type="l", pch=23, lty=3)
legend("topleft", c("Normal", "Sem casamentos", "Idêntica"), cex=0.8, lty=1:3, pch=21:23, lwd=1, bty="n")
dev.off()

# Greedy
t <- read.table("gree.csv", header=FALSE, sep='\t')
pdf(file="gree.pdf", height=5, width=5)
plot(t[,1], t[,4],  type="l", pch=22, lty=2, xlab="n", ylab="Comparações")
lines(t[,1], t[,3], type="o")
lines(t[,1], t[,2], type="l", pch=23, lty=3)
legend("topleft", c("Normal", "Sem casamentos", "Idêntica"), cex=0.8, lty=1:3, pch=21:23, lwd=1, bty="n")
dev.off()

# Dynamic vs Geedy
t <- read.table("dynaVsGree.csv", header=FALSE, sep='\t')
pdf(file="dynaVsGree.pdf", height=5, width=5)
plot(t[,2], t[,3],  type="o", xlab="n", ylab="Tempo (ms)")
lines(t[,2], t[,4], type="o", pch=22, lty=2)
legend("topleft", c("PD", "Guloso"), cex=0.8, lty=1:2, pch=21:22, lwd=1, bty="n")
dev.off()

# Precisão por tamanho
t <- read.table("precisionBySize.csv", header=FALSE, sep='\t')
pdf(file="precisionBySize.pdf", height=5, width=5)
plot(t[,2], t[,3],  type="o", xlab="n", ylab="%")
lines(t[,2], t[,4], type="o", pch=22, lty=2)
legend("topright", c("Precisão", "Erro"), cex=0.8, lty=1:2, pch=21:22, lwd=1, bty="n")
dev.off()

# Precisão por k
t <- read.table("precisionByK.csv", header=FALSE, sep='\t')
pdf(file="precisionByK.pdf", height=5, width=5)
plot(t[,1], t[,3],  type="o", xlab="k", ylab="%")
lines(t[,1], t[,4], type="o", pch=22, lty=2)
legend("topleft", c("Precisão", "Erro"), cex=0.8, lty=1:2, pch=21:22, lwd=1, bty="n")
dev.off()
