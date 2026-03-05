# Read from the file file.txt and print its transposed content to stdout.
awk '
{
    for(i=1;i<=NF;i++){
        a[NR,i]=$i
    }
}
NF>p{p=NF}
END{
    for(i=1;i<=p;i++){
        str=a[1,i]
        for(j=2;j<=NR;j++){
            str=str" "a[j,i]
        }
        print str
    }
}' file.txt